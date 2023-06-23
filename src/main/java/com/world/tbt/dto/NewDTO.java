package com.world.tbt.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.world.tbt.entity.PostRating;
import lombok.*;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewDTO extends AbstractDTO<NewDTO> {
	private PostStatus status;
	@NotBlank
	@NotNull
	@Size(min = 10, max = 200, message
			= "code must be between 10 and 200 characters")
	private String title;
	private String thumbnail;
	private String name;
	@NotBlank
	@NotNull
	@Size(min = 10, max = 200, message
			= "code must be between 10 and 200 characters")
	private String shortDescription;
	@NotBlank
	@NotNull
	private String content;
	@NotBlank
	private String categoryCode;
	private CategoryDTO category;
	private String messageDenied;

	private Set<PostRating> ratings;
}
