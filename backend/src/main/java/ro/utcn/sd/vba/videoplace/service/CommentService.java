package ro.utcn.sd.vba.videoplace.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vba.videoplace.dto.CommentDTO;
import ro.utcn.sd.vba.videoplace.entity.Comment;
import ro.utcn.sd.vba.videoplace.entity.User;
import ro.utcn.sd.vba.videoplace.entity.Video;
import ro.utcn.sd.vba.videoplace.repository.api.RepositoryFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<CommentDTO> findCommentsByVideo(String link) {
        List<Video> videos = repositoryFactory.createVideoRepository().findAll();
        for(Video v: videos){
            if(v.getLink().equals(link)) {
                List<Comment> comments = repositoryFactory.createCommentRepository().findAllFromVideo(v.getId());
                return comments.stream().map(CommentDTO::ofEntity).collect(Collectors.toList());
            }

        }
        return new ArrayList<>();
        //System.out.println(videoId);

    }

    @Transactional
    public CommentDTO saveComment(String comment, String username, String link) throws Exception {
        Optional<User> user = repositoryFactory.createUserRepository().findByUsername(username);
        List<Video> videos = repositoryFactory.createVideoRepository().findAll();
        int id = 0;
        for(Video v: videos){
            if(v.getLink().equals(link)) {
                id = v.getId();
            }

        }
        Comment newComment = new Comment(comment,
                username,
                user.get().getId(),
                id);

        Optional<Video> video = repositoryFactory.createVideoRepository().findById(newComment.getVideoId());
        if (video.isPresent()) {
            return CommentDTO.ofEntity(repositoryFactory.createCommentRepository().save(newComment));
        } else {
            throw new Exception("Video not found");
        }
    }


}

