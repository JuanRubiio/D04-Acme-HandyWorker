
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.TutorialRepository;

@Service
@Transactional
public class TutorialService {

	//Managed repo
	@Autowired
	private TutorialRepository	tutorialRepository;

	//Supporting services

}
