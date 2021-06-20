package cn.edu.zust.se.contestmanage.service.impl;

import cn.edu.zust.se.contestmanage.dao.*;
import cn.edu.zust.se.contestmanage.dto.ContestDto;
import cn.edu.zust.se.contestmanage.dto.StudentDto;
import cn.edu.zust.se.contestmanage.dto.TeacherDto;
import cn.edu.zust.se.contestmanage.entity.*;
import cn.edu.zust.se.contestmanage.form.ContestPage;
import cn.edu.zust.se.contestmanage.form.RegisterForm;
import cn.edu.zust.se.contestmanage.service.ContestService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(version = "${contest.service.version}",application = "${dubbo.application.id}")
public class ContestServiceImpl implements ContestService {
    @Autowired
    ContestDao contestDao ;
    @Autowired
    TeamDao teamDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    S2TDao s2TDao;
    @Autowired
    T2TDao t2TDao;

    private ContestDto e2d(ContestEntity tContest) {
        ContestDto contest = new ContestDto();
        BeanUtils.copyProperties(tContest,contest);
        return contest;
    }

    private List<ContestDto> e2d(List<ContestEntity> tCourse) {
        if(tCourse == null || tCourse.size() == 0) {
            return new ArrayList<>();
        }
        List<ContestDto> courseDtos = new ArrayList<>();
        for(ContestEntity course : tCourse) {
            if(course != null) {
                courseDtos.add(e2d(course));
            }
        }
        return courseDtos;
    }

    private StudentDto e2dS(StudentEntity tStudent) {
        StudentDto student = new StudentDto();
        BeanUtils.copyProperties(tStudent,student);
        return student;
    }

    private List<StudentDto> e2dS(List<StudentEntity> tStudent) {
        if(tStudent == null || tStudent.size() == 0) {
            return new ArrayList<>();
        }
        List<StudentDto> studentDtos = new ArrayList<>();
        for(StudentEntity course : tStudent) {
            if(course != null) {
                studentDtos.add(e2dS(course));
            }
        }
        return studentDtos;
    }
    private TeacherDto e2dT(TeacherEntity tTeacher) {
        TeacherDto teacher = new TeacherDto();
        BeanUtils.copyProperties(tTeacher,teacher);
        return teacher;
    }

    private List<TeacherDto> e2dT(List<TeacherEntity> tTeacher) {
        if(tTeacher == null || tTeacher.size() == 0) {
            return new ArrayList<>();
        }
        List<TeacherDto> teacherDtos = new ArrayList<>();
        for(TeacherEntity course : tTeacher) {
            if(course != null) {
                teacherDtos.add(e2dT(course));
            }
        }
        return teacherDtos;
    }

    @Override
    public String createContest(ContestDto contestDto) {
        List<ContestEntity> contestEntityList = contestDao.findByName(contestDto.getName());
        if(contestEntityList.size()!=0){
            return "创建失败：该竞赛已存在，无需创建！";
        }else{
            ContestEntity contest = new ContestEntity();
            contest.setName(contestDto.getName());
            contest.setSponsor(contestDto.getSponsor());
            contest.setNumberLimit(contestDto.getNumberLimit());
            contest.setRegisterStartTime(contestDto.getRegisterStartTime());
            contest.setRegisterEndTime(contestDto.getRegisterEndTime());
            contest.setContestStartTime(contestDto.getContestStartTime());
            contest.setContestEndTime(contestDto.getContestEndTime());
            contest.setDescription(contestDto.getDescription());
            contestDao.save(contest);
            return "success";
        }
    }

    // TODO: 2021/6/14 增加查询功能，查询已经结束的比赛，进行中的比赛，未开始的比赛，根据比赛名称进行搜索
    @Override
    public ContestPage getContestList(int pageNum, int pageSize) {
        //Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC,"id"));
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ContestEntity> contests = contestDao.findAll(pageable);
        ContestPage cp = new ContestPage();
        System.out.println("*********************ALL*********************");
        System.out.println(pageNum+1);
        System.out.println(pageNum-1);
        System.out.println(contests.getTotalPages());
        System.out.println("*********************************************\n");
        cp.setContents(e2d(contests.getContent()));
        cp.setHasNext(contests.hasNext());
        cp.setNext(pageNum+1);
        cp.setHasPrevious(contests.hasPrevious());
        cp.setPrevious(pageNum-1);
        cp.setTotalPages(contests.getTotalPages());
        return cp;
    }

    @Override
    public ContestDto findById(int id) {
        ContestEntity contestEntity = contestDao.findById(id).orElse(null);
        return e2d(contestEntity);
    }

    @Override
    public String updateContest(ContestDto contestDto) {
        ContestEntity contest = new ContestEntity();
        contest.setId(contestDto.getId());
        contest.setName(contestDto.getName());
        contest.setSponsor(contestDto.getSponsor());
        contest.setNumberLimit(contestDto.getNumberLimit());
        contest.setRegisterStartTime(contestDto.getRegisterStartTime());
        contest.setRegisterEndTime(contestDto.getRegisterEndTime());
        contest.setContestStartTime(contestDto.getContestStartTime());
        contest.setContestEndTime(contestDto.getContestEndTime());
        contest.setDescription(contestDto.getDescription());
        contestDao.save(contest);
        return "success";
    }

    @Override
    public List<RegisterForm> getRegisterForm(int id) {
        List<TeamEntity> teams = teamDao.findByCid(id);
        List<RegisterForm> registers = new ArrayList<>();
        for(TeamEntity t:teams){
            if (t.getTCheck()==0)   continue;
            if (t.getSCheck()!=t.getTeamNumber())   continue;
            RegisterForm rf = new RegisterForm();
            List<StudentEntity> stus = new ArrayList<>();
            List<S2TEntity> sids = s2TDao.findByTid(t.getId());
            for(S2TEntity s:sids){
                StudentEntity stu = studentDao.findById(s.getSid()).orElse(null);
                if(s.getId()==sids.get(0).getId()) rf.setPhone(stu.getPhone());
                stus.add(stu);
            }
            int teacherId = t2TDao.findByTid(t.getId()).get(0).getTeacherId();
            TeacherEntity teacher = teacherDao.findById(teacherId).orElse(null);
            rf.setCid(id);
            rf.setName(t.getName());
            rf.setStudents(e2dS(stus));
            rf.setTid(t.getId());
            rf.setTeacher(e2dT(teacher));
            rf.setScore(t.getScore());
            registers.add(rf);
        }
        return registers;
    }

    @Override
    public String updateRegisterForm(List<RegisterForm> register) {
        //List<RegisterForm> rfs = registerData.getList();
        for(RegisterForm rf:register){
            TeamEntity team = teamDao.findById(rf.getTid()).orElse(null);
            team.setScore(rf.getScore());
            teamDao.save(team);
        }
        return "success";
    }

    @Override
    public ContestPage searchContest(int type, String keyword, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC,"id"));
        Page<ContestEntity> contests = null;
        if(type==0)
            contests = contestDao.findByNameLike("%"+keyword+"%",pageable);

        if(type==1)
            contests = contestDao.findBySponsorLike("%"+keyword+"%",pageable);
        ContestPage cp = new ContestPage();
        cp.setContents(e2d(contests.getContent()));
        cp.setHasNext(contests.hasNext());
        cp.setNext(pageNo+1);
        cp.setHasPrevious(contests.hasPrevious());
        cp.setPrevious(pageNo-1);
        cp.setTotalPages(contests.getTotalPages());
        return cp;
    }

    @Override
    public ContestPage listContestByTime(int status, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<ContestEntity> contests = null;
        SimpleDateFormat sj1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String now = sj1.format(date);
        ContestPage cp = new ContestPage();
        if(status==0){
            try {
                Specification specification = new Specification() {
                    @Override
                    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                        //增加筛选条件
                        Predicate predicate = cb.conjunction();
                        //当前时间小于报名开始的时间
                        if (now != null && !now.trim().equals("")) {
                            predicate.getExpressions().add(cb.greaterThan(root.get("registerStartTime").as(String.class), now));
                        }
                        return predicate;
                    }
                };
                contests = contestDao.findAll(specification, pageable);
            } catch (Exception e) {
                    e.printStackTrace();
            }
        }
        if(status==1){
            try {
                Specification specification = new Specification() {
                    @Override
                    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                        //增加筛选条件
                        Predicate predicate = cb.conjunction();
                        //起始日期
                        if (now != null && !now.trim().equals("")) {
                            predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("registerStartTime").as(String.class), now));
                        }
                        //结束日期
                        if (now != null && !now.trim().equals("")) {
                            predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("contestEndTime").as(String.class), now));
                        }
                        return predicate;
                    }
                };
                contests = contestDao.findAll(specification, pageable);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(status==2){
            try {
                Specification specification = new Specification() {
                    @Override
                    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                        //增加筛选条件
                        Predicate predicate = cb.conjunction();
                        //结束日期
                        if (now != null && !now.trim().equals("")) {
                            predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("contestEndTime").as(String.class), now));
                        }
                        return predicate;
                    }
                };
                contests = contestDao.findAll(specification, pageable);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        cp.setContents(e2d(contests.getContent()));
        cp.setHasNext(contests.hasNext());
        cp.setNext(pageNo+1);
        cp.setHasPrevious(contests.hasPrevious());
        cp.setPrevious(pageNo-1);
        cp.setTotalPages(contests.getTotalPages());
        System.out.println("*********************SEARCH*********************");
        System.out.println(contests.getContent().size());
        System.out.println(pageNo+1);
        System.out.println(pageNo-1);
        System.out.println(contests.getTotalPages());
        System.out.println("*********************************************\n");
        return cp;
    }



    /*
     public List<ContestDto> findContestByName(String name) {
        List<ContestEntity> contestEntityList = contestDao.findByName(name);
        return e2d(contestEntityList);
    }
     */

}
