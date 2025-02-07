package com.meli.socialmeli.dto.response;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.socialmeli.dto.PostDto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class PostFromFollowedDto {
    @JsonProperty("user_id")
    private Integer userId;
    private List<PostDto> posts;
}
