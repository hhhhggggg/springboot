package com.springboot.web.DTO;

import lombok.*;

@Getter
@NoArgsConstructor
public class PostsUpdateRequstDto
{
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequstDto(String title, String content)
    {
        this.title = title;
        this.content = content;
    }
}
