package service;

import dto.CourseAnnouncementDTO;
import entity.AnnouncementEntity;
import mapper.AnnouncementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementMapper mapper;

    @Autowired
    public AnnouncementServiceImpl(AnnouncementMapper mapper) {
        this.mapper = mapper;
    }

    public int update(AnnouncementEntity announcementEntity) {
        return mapper.update(announcementEntity);
    }

    public int delete(int id) {
        return mapper.delete(id);
    }

    public int save(AnnouncementEntity announcementEntity) {
        return mapper.save(announcementEntity);
    }

    public AnnouncementEntity selectOne(int id) {
        return mapper.selectOne(id);
    }

    public List<AnnouncementEntity> selectAll() {
        return mapper.selectAll();
    }

    public List<AnnouncementEntity> selectCourseAnnouncement(int courseId) {
        return mapper.selectCourseAnnouncement(courseId);
    }

    public List<CourseAnnouncementDTO> selectTchAnnouncement(int userId) {
        return mapper.selectTchAnnouncement(userId);
    }

    public List<CourseAnnouncementDTO> selectStuAnnouncement(int userId) {
        return mapper.selectStuAnnouncement(userId);
    }
}
