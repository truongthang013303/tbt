package com.world.tbt.dto;
import lombok.Data;
import javax.validation.constraints.*;
import java.util.Date;

@Data
public class CommentDTO {
    private Long id;
    private Date createdDate;
    private Date modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private String titlePost;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 200, message
            = "Content of a comment must be between 1 and 200 characters")
    private String content;
    @NotNull
    @Positive
    @Max(value = 1000, message = "idPost should not be greater than 1000")
    private Long idPost;
    @Max(value = 1000, message = "userid should not be greater than 1000")
    @Positive
    private Long userid;
}
