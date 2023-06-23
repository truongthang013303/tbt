package com.world.tbt.service.impl;

import com.world.tbt.converter.NewConverter;
import com.world.tbt.dto.NewDTO;
import com.world.tbt.dto.RatingDTO;
import com.world.tbt.entity.NewEntity;
import com.world.tbt.entity.PostRating;
import com.world.tbt.entity.UserEntity;
import com.world.tbt.repository.CategoryRepository;
import com.world.tbt.repository.NewRepository;
import com.world.tbt.repository.UserRepository;
import com.world.tbt.service.INewService;
import com.world.tbt.utils.UpLoadFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

;


@Service
public class NewService implements INewService {
	
	@Autowired
	private NewRepository newRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private NewConverter newConverter;
	@Autowired
	private UpLoadFileUtils upLoadFileUtils;
	private static final Logger LOGGER = LoggerFactory.getLogger(NewService.class);
	@Override
	public ResponseEntity<?> rating(RatingDTO ratingDTO) {
		UserEntity userEntity = userRepository.findOneById(ratingDTO.getUserid());
		NewEntity newEntity;
		try{
			newEntity = newRepository.findOneById(ratingDTO.getPostid());
		}
		catch (Exception e){
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Post ID not found");
		}
		//newEntity.addRating(userEntity, rating);
		newEntity.getRatings().add(PostRating.builder()
				.news(newEntity)
				.user(userEntity)
				.rating(ratingDTO.getRating())
				//.id(PostRatingKey.builder().postId(newEntity.getId()).userId(userEntity.getId()).build())
				.build());
		NewEntity saved;
		try {
			saved = newRepository.save(newEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Process saving rating is failed - You have rated this post");
		}
		return ResponseEntity.ok(saved);
	}
	@Override
	public Page<NewDTO> findAllByCreatedBy(String createdBy, Pageable pageable){
		Page<NewEntity> entities = newRepository.findAllByCreatedBy(createdBy, pageable);
		Page<NewDTO> dtoPage = convertPageEntityToPageDTO(entities);
		return dtoPage;
	}

	public Page<NewDTO> convertPageEntityToPageDTO(Page<NewEntity> entities)
	{
		Page<NewDTO> dtoPage = entities.map(new Function<NewEntity, NewDTO>() {
			@Override
			public NewDTO apply(NewEntity entity) {
				NewDTO dto = new NewDTO();
				dto =newConverter.toDto(entity);
				return dto;
			}
		});
		return dtoPage;
	}

	//Lấy lên danh sách bài viết không phân biệt thể loại và không phân biệt status
	@Override
	public Page<NewDTO> findByPage(Pageable pageable) {
		Page<NewEntity> entities = newRepository.findAll(pageable);
		Page<NewDTO> dtoPage = convertPageEntityToPageDTO(entities);
		return dtoPage;
	}
	//Lấy lên danh sách bài viết không phân biệt thể loại và status theo yêu cầu của client
	@Override
	public Page<NewDTO> findByPage(Integer status, Pageable pageable) {
		Page<NewEntity> entities = newRepository.findAllByStatus(status, pageable);

		Page<NewDTO> dtoPage = convertPageEntityToPageDTO(entities);

		return dtoPage;
	}

	@Override
	public Long getTotalItem() {
		return newRepository.count();
	}

	//DELETE_POST ở ngoài trang danh sách mới xóa được không cần hiển thị trang chi tiết bài viết nên không cần truy xuất thông tin bài viết bất kỳ lên
/*	@PreAuthorize("hasRole('ROLE_ADMIN')  OR hasAnyAuthority('VIEW_POST','VIEW_SELF_POST','PUBLISH_POST','UPDATE_POST','UPDATE_SELF_POST')")
	//Ngoài những role,authorities đã kể thì còn lại VIEW_SEFT_POST hoac UPDATE_SELF_POST đều phải qua fillter ktra xem bài viết lấy lên có phải do principal đang đăng nhập create không nếu đúng mới trả về view + data
	@PostAuthorize("hasRole('ROLE_ADMIN') OR hasAnyAuthority('VIEW_POST','PUBLISH_POST','UPDATE_POST') OR returnObject.createdBy == authentication.principal.username")*/
	@Override
	public NewDTO findById(long id) {
		NewEntity newEntity = newRepository.findOneById(id);
		NewDTO newDto = newConverter.toDto(newEntity);// convert từ newEntity sang dạng data DTO
		return newDto;
	}

    @Override
    public NewDTO findByIdAndStatus(long id, int status) {
		NewEntity newEntity = newRepository.findOneByIdAndStatus(id, status);
		NewDTO newDto;
		if(newEntity!=null)
		{
			newDto = newConverter.toDto(newEntity);
		}
		else{
			return null;
		}
		return newDto;
    }

	//Lấy lên danh sách bài viết theo thể loại cụ thể và có status=Đã duyệt
	@Override
	public Page<NewDTO> findAllByCategoryCodePage(String cateCode, Integer status, Pageable pageable)
	{
		Page<NewEntity> entities = newRepository.findAllByCategoryCodeAndStatus(cateCode, status, pageable);

		//Page<NewEntity> entities = newRepository.findAllByCategoryCodeAndStatus("kinh-doanh", 1, PageRequest.of(0,2));

		Page<NewDTO> dtoPage = convertPageEntityToPageDTO(entities);
		return dtoPage;
	}

	@PreAuthorize("#dto.id==null?hasAuthority('CREATE_POST'):hasAuthority('UPDATE_POST') OR #dto.createdBy==authentication.principal.username")
//	@PreFilter("#dto.id==0?hasAuthority('CREATE_POST'):#dto.createdBy==authentication.principal.username OR hasAuthority('UPDATE_ALL_POST')")
	@Override
	@Transactional
	public NewDTO saveOrUpdate(NewDTO dto) throws Exception {
		NewEntity newEntity = new NewEntity();
		//Update post
		if (dto.getId() != null) {
			NewEntity oldNewEnity = newRepository.findOneById(dto.getId());
			//oldNewEnity.setCategory(category);
			newEntity = newConverter.toEntity(oldNewEnity, dto);
			//Trong trường hợp update bài viết: nếu bviet gửi lên có status!=từ chối sẽ không có message(lý do từ chối bviet)
			//Ngược lại nếu là bviet bị từ chối thì trong toEntity() sẽ gán message gửi lên từ client vào cho entity để lưu xuống db

		}
		else if(newRepository.existsByTitle(dto.getTitle())==false)//Create a new post
		{
			NewDTO dtoAfterSaveFile = upLoadFileUtils.uploadFile(dto);
			//after upload img convert dto to entity, set cate, save
			newEntity = newConverter.toEntity(dtoAfterSaveFile);
			//newEntity.setCategory(category);
		}else{
			return null;
		}
		//Mỗi khi thêm mới hay cập nhập 1 bài viết trạng thái của nó sẽ quay lại chờ duyệt, gán status=0=chờ duyệt trước khi lưu xuống
		newEntity.setStatus(0);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//Nếu người dùng đang đăng nhập có ROLE_ADMIN cho phép nó sửa lại status=status nó gửi lên còn lại auto status=0[chờ duyệt]
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			newEntity.setStatus(dto.getStatus().getPostStatusCode());
		}
		//Trường hợp lưu 1 newEntity mà không có category lúc truy vấn lấy bviet lên sẽ bị lỗi NullPointerException ở trường category của NewEntity
		if(!newEntity.isNotHaveCategory()){
			return newConverter.toDto(newRepository.save(newEntity));
		}else
		{return null;}
		//return dto;
	}
	//Ngoại trừ việc user có author: PUBLISH_POST-duyệt bất kỳ bài viết nào, hoặc role_admin còn lại PUBLISH_SELF_POST-duyệt bài viết của bản thân thì phải ktra bài viết yêu cầu duyệt có create bởi principal đang đăng nhập không
	//Thực chất là cập nhập lại status của bài viết,chỉ cập nhập trường status
	@PreAuthorize("hasAuthority('PUBLISH_POST') OR hasRole('ROLE_ADMIN') OR #dto.createdBy==authentication.principal.username")
	@Override
	public NewDTO publishPost(NewDTO dto){
		NewEntity newEntity = new NewEntity();
		if (dto.getId() != null) {
			newEntity = newRepository.findOneById(dto.getId());
			newEntity.setStatus(dto.getStatus().getPostStatusCode());
		}
		//Trường hợp lưu 1 bài vết có category là rỗng khi covert qua dto sẽ có newDTO.setCategoryCode(newEntity.getCategory().getCode()); sẽ sinh ra lỗi nullPointerException
		if(newEntity.getContent().equals("")&& newEntity.getShortDescription().equals("")&&newEntity.getTitle().equals("")&&newEntity.getThumbnail().equals(""))
		{
			return null;
		}
		return newConverter.toDto(newRepository.save(newEntity));
	}

	@Override
	public Long countByStatus(Integer status) {
		return newRepository.countByStatus(status);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('DELETE_POST')")
	@Override
	@Transactional
	public void delete(long[] ids) 
	{
		for (long id: ids) 
		{
			if(newRepository.existsById(id)){
				newRepository.deleteById(id);
			}else{
				LOGGER.warn("id:"+id+"not exists");
			}
		}
	}

	
	@Override
	public List<NewDTO> findByTitleContainingAndStatus(String searchWord)
	{
		List<NewEntity> listNewEntity = newRepository.findByTitleContainingAndStatus(searchWord, 1);
		List<NewDTO> listNewDTO = new ArrayList<>();
		for (NewEntity newEntity : listNewEntity) 
		{
			listNewDTO.add(newConverter.toDto(newEntity));
		}
		return listNewDTO;
	}
	//Tim bai vai viet trong title co keyword da cho khong phan biet Status
	@Override
	public List<NewDTO> findByTitleContaining(String searchWord) {
		List<NewEntity> listNewEntity = newRepository.findByTitleContaining(searchWord);
		List<NewDTO> listNewDTO = new ArrayList<>();
		for (NewEntity newEntity : listNewEntity)
		{
			listNewDTO.add(newConverter.toDto(newEntity));
		}
		return listNewDTO;
	}
	@Override
	public List<NewDTO> findByTitleContainingAndCreatedBy(String search, String createdBy) {
		List<NewEntity> listNewEntity = newRepository.findByTitleContainingAndCreatedBy(search, createdBy);
		List<NewDTO> listNewDTO = new ArrayList<>();
		for (NewEntity newEntity : listNewEntity)
		{
			listNewDTO.add(newConverter.toDto(newEntity));
		}
		return listNewDTO;
	}

	@Override
	public boolean existsById(Long id)
	{
		return newRepository.existsById(id);
	}

	@Override
	public Page<NewDTO> findAllByCategoryId(Long categoryid, Pageable pageable){
		Page<NewEntity> entities = newRepository.findAllByCategoryId(categoryid, pageable);
		Page<NewDTO> dtoPage = convertPageEntityToPageDTO(entities);
		return dtoPage;
	}
}

