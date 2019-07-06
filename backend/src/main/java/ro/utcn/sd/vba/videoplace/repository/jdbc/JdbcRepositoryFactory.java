package ro.utcn.sd.vba.videoplace.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vba.videoplace.repository.api.*;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty (name = "videoplace.repository-type",havingValue = "JDBC")
public class JdbcRepositoryFactory implements RepositoryFactory{
    private final JdbcVideoRepository videoRepository;
    private final JdbcCommentRepository commentRepository;
    private final JdbcUserRepository userRepository;
    private final JdbcTagRepository tagRepository;
    private final JdbcVideoTagRepository videoTagRepository;

    @Override
    public VideoRepository createVideoRepository() {
        return videoRepository;
    }

    @Override
    public CommentRepository createCommentRepository() {
        return commentRepository;
    }

    @Override
    public UserRepository createUserRepository() {
        return userRepository;
    }

    @Override
    public TagRepository createTagRepository() {
        return tagRepository;
    }

    @Override
    public VideoTagRepository createVideoTagRepository() {
        return videoTagRepository;
    }
}
