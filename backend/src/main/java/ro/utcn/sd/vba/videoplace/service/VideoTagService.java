package ro.utcn.sd.vba.videoplace.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vba.videoplace.dto.VideoDTO;
import ro.utcn.sd.vba.videoplace.dto.VideoTagDTO;
import ro.utcn.sd.vba.videoplace.entity.Video;
import ro.utcn.sd.vba.videoplace.entity.VideoTag;
import ro.utcn.sd.vba.videoplace.entity.Tag;
import ro.utcn.sd.vba.videoplace.repository.api.RepositoryFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoTagService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<VideoDTO> findVideosByTag(Tag tag){
        List<VideoDTO> videoDTOList = new ArrayList<>();
        List<Video> videos = repositoryFactory.createVideoTagRepository().
                findVideosByTag(tag.getName(),repositoryFactory.createVideoRepository().findAll());
        for(Video q : videos){
            VideoDTO qDTO = VideoDTO.ofEntity(q);
            List<Tag> tags = findTagsByVideo(q);
            StringBuilder tagString = new StringBuilder();
            for (Tag t : tags) {
                tagString.append(t.getName()).append(",");
            }
            qDTO.setTags(tagString.toString().substring(0, tagString.toString().length() - 1));
            if(!videoDTOList.contains(qDTO))
                videoDTOList.add(qDTO);
        }
        return videoDTOList;
    }

    @Transactional
    public List<Tag> findTagsByVideo(Video video){
        return repositoryFactory.createVideoTagRepository().
                findTagsByVideo(video,repositoryFactory.createTagRepository().findAll());
    }

    @Transactional
    public VideoTagDTO saveVideoTag(VideoTag videoTag) throws SQLException {
        return VideoTagDTO.ofEntity(repositoryFactory.createVideoTagRepository().save(videoTag));
    }
}
