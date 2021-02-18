package com.kimtaehyun84.springboot_webservice.service.posts;

import com.kimtaehyun84.springboot_webservice.domain.posts.Posts;
import com.kimtaehyun84.springboot_webservice.domain.posts.PostsRepository;
import com.kimtaehyun84.springboot_webservice.web.dto.PostsListResponseDto;
import com.kimtaehyun84.springboot_webservice.web.dto.PostsResponseDto;
import com.kimtaehyun84.springboot_webservice.web.dto.PostsSaveRequestDto;
import com.kimtaehyun84.springboot_webservice.web.dto.PostsUpdateRequestDto;
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
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No data. id="+ id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No data. id="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No data. id="+id));
        postsRepository.delete(posts);
    }
}
