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

    @Override
    public int update(AnnouncementEntity announcementEntity) {
        return mapper.update(announcementEntity);
    }

    @Override
    public int delete(int id) {
        return mapper.delete(id);
    }

    @Override
    public int save(AnnouncementEntity announcementEntity) {
        return mapper.save(announcementEntity);
    }

    @Override
    public AnnouncementEntity selectOne(int id) {
        return mapper.selectOne(id);
    }

    @Override
    public List<AnnouncementEntity> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public List<AnnouncementEntity> selectCourseAnnouncement(int courseId) {
        return mapper.selectCourseAnnouncement(courseId);
    }

    @Override
    public List<CourseAnnouncementDTO> selectTchAnnouncement(int userId) {
        return mapper.selectTchAnnouncement(userId);
    }

    @Override
    public List<CourseAnnouncementDTO> selectStuAnnouncement(int userId) {
        return mapper.selectStuAnnouncement(userId);
    }
}
