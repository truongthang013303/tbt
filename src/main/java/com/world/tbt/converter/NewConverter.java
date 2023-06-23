package com.world.tbt.converter;

import com.world.tbt.dto.CategoryDTO;
import com.world.tbt.dto.NewDTO;
import com.world.tbt.dto.PostStatus;
import com.world.tbt.entity.NewEntity;
import com.world.tbt.repository.CategoryRepository;
import com.world.tbt.utils.UpLoadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
@Component
public class NewConverter 
{
	@Autowired
	private CategoryConverter categoryConverter;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UpLoadFileUtils upLoadFileUtils;

	public NewDTO toDto(NewEntity newEntity) {
		NewDTO newDTO = new NewDTO();
		newDTO.setId(newEntity.getId());
		newDTO.setTitle(newEntity.getTitle());
		newDTO.setShortDescription(newEntity.getShortDescription());
		newDTO.setContent(newEntity.getContent());
		newDTO.setThumbnail(newEntity.getThumbnail());
		CategoryDTO categoryDTO = categoryConverter.toDto(newEntity.getCategory());
		newDTO.setCategory(categoryDTO);
		/*entity.getCategory(); sẽ lấy về được 1 đối tượng CategoryEntity. Sau đó chúng ta sẽ dụng getter getCode() để lấy ra được code vd:'the-thao'
		và gán cho thuộc tính CategoryCode của NewDTO*/
		//Trường hợp chúng ta post hay put bị lỗi trong DB sẽ xuất hiện các bài viết không có category do đó ko truy xuất được .getCategory()
		try
		{newDTO.setCategoryCode(newEntity.getCategory().getCode());}
		catch (Exception e)
		{
			System.out.println("toDto(NewEntity newEntity)--class NewConverter-[newEntity.getCategory() is null-NullPointerException]\"");
			newDTO.setCategoryCode("mobile");
		}
		//Convert status repersent by interger[0,1,2,3] in MySQL to ENUM status in java code
		newDTO.setStatus(PostStatus.getPostStatusEnumByInteger(newEntity.getStatus()));

		newDTO.setCreatedDate(newEntity.getCreatedDate());
		newDTO.setCreatedBy(newEntity.getCreatedBy());
		newDTO.setModifiedBy(newEntity.getModifiedBy());
		newDTO.setModifiedDate(newEntity.getModifiedDate());

		newDTO.setMessageDenied(newEntity.getMessage());
		//Get ratings of post
		try{
			newDTO.setRatings(newEntity.getRatings());
		}catch (Exception e){
			System.out.println("toDto(NewEntity newEntity)--class NewConverter-[newEntity.getRatings() is null-NullPointerException]");
			newDTO.setRatings(new HashSet<>());
		}
		newDTO.setMessageDenied(newEntity.getMessage());
		return newDTO;
	}
	// hàm này dùng cho thêm mới bài viết. Truyền vào một bài viết đã có dữ liệu từ client dạng DTO sau đó chuyển nó vào một newEnity mới hoàn toàn
	public NewEntity toEntity(NewDTO newDTO) {
		NewEntity newEntity = new NewEntity();
		newEntity.setTitle(newDTO.getTitle());
		newEntity.setShortDescription(newDTO.getShortDescription());
		newEntity.setContent(newDTO.getContent());
		newEntity.setThumbnail(newDTO.getThumbnail());
		newEntity.setMessage(newDTO.getMessageDenied());
		newEntity.setCategory(categoryRepository.findOneByCode(newDTO.getCategoryCode()));
		return newEntity;
	}
	/*hàm này dùng cho update chúng ta chuyền vào bài viết được lấy lên từ database theo id, truyền thêm 1 bài viết có dữ liệu muốn thay đổi của user nhập(newDTO)
	sau đó gán những thôg tin mà user muốn thay đổi có trong newDTO sang cho bài viết mà user muốn thay đổi: NewEntity newEntity = newRepository.findOne(id);
	bài viết newEntity này đã có sẵn được tìm lên bằng findOne(newDTO.getId()); chứ khôg phải 1 bài viết mới hoàng toàn như save()*/
	public NewEntity toEntity(NewEntity newEntity, NewDTO newDTO) 
	{
		newEntity.setTitle(newDTO.getTitle());
		newEntity.setShortDescription(newDTO.getShortDescription());
		newEntity.setContent(newDTO.getContent());
		newEntity.setMessage(newDTO.getMessageDenied());
		if(newDTO.getThumbnail().isEmpty() && newDTO.getName().isEmpty())
		{
			System.out.println("toEntity(NewEntity newEntity, NewDTO newDTO)--Update post but not update image");
		}
		else// còn nếu như client gửi lên thumbnail:stringBase64 tất nhiên khác với thumbnail: ảnh trong DB, tức là sửa đổi có sửa cả ảnh, cta sẽ tiến hành lưu ảnh, return lại: tên ảnh, sau đó lấy tên ảnh trả về gán cho thumbnail và lưu xuống DB
		{
				NewDTO newDTOAfterSaveFile = upLoadFileUtils.uploadFile(newDTO);
				newEntity.setThumbnail(newDTOAfterSaveFile.getThumbnail());// ban đầu .getThumbnail() là 1 chuỗi base64 sau khi lưu sẽ chuyển thành tên của hình ảnh chúng ta lưu xuống DB
				System.out.println("toEntity(NewEntity newEntity, NewDTO newDTO)--Update post and update image too");
		}
		newEntity.setCategory(categoryRepository.findOneByCode(newDTO.getCategoryCode()));
		if(newDTO.getStatus()!=PostStatus.TUCHOI)
		{
			newEntity.setMessage("");
		}
		return newEntity;
	}
}
