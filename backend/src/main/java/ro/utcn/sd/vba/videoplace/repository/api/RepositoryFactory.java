package ro.utcn.sd.vba.videoplace.repository.api;

public interface RepositoryFactory {
    CommentRepository createCommentRepository();
    UserRepository createUserRepository();
    TagRepository createTagRepository();
    VideoRepository createVideoRepository();
    VideoTagRepository createVideoTagRepository();
}
