package com.springboot.service;

import com.springboot.domain.posts.Posts;
import com.springboot.domain.posts.PostsRepository;
import com.springboot.web.DTO.PostsListResponseDto;
import com.springboot.web.DTO.PostsResponseDto;
import com.springboot.web.DTO.PostsSaveRequestDto;
import com.springboot.web.DTO.PostsUpdateRequstDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service

public class PostsService {
    private final PostsRepository postsRepository;
    @Transactional
    public Long save(PostsSaveRequestDto requestDto)
    {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequstDto requstDto)
    {
        Posts posts = postsRepository.findById(id).orElseThrow(()->new
                IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        posts.update(requstDto.getTitle(), requstDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()->new
                IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc()
    {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }


}
