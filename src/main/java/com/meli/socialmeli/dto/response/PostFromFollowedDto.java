package com.meli.socialmeli.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.socialmeli.dto.PostDto;
import lombok.*;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostFromFollowedDto {
    @JsonProperty("user_id")
    private Integer userId;
    private List<PostDto> posts;
}
