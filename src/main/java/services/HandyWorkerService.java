//
// package services;
//
// import java.util.ArrayList;
// import java.util.List;
//
// import javax.transaction.Transactional;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
//
// import repositories.HandyWorkerRepository;
// import security.Authority;
// import security.UserAccount;
// import domain.HandyWorker;
//
// @Service
// @Transactional
// public class HandyWorkerService {
//
// @Autowired
// private HandyWorkerRepository handyworkerRepository;
//
// @Autowired
// private MessageBoxService messageboxService;
//
// @Autowired
// private CurriculumService curriculumService;
//
//
// public HandyWorker create() {
// HandyWorker res;
//
// res = new HandyWorker();
// final UserAccount userAccount = new UserAccount();
// final List<Authority> authorities = new ArrayList<Authority>();
// final Authority authority = new Authority();
// authority.setAuthority(Authority.HANDYWORKER);
// authorities.add(authority);
// userAccount.setAuthorities(authorities);
//
// res.setUserAccount(userAccount);
// this.messageboxService.addDefaultMessageBoxs(res);
//
// res.setCurriculum(this.curriculumService.create());
//
// return res;
// }
//}
