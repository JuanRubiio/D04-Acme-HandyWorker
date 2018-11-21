
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.SectionRepository;

@Service
@Transactional
public class SectionService {

	//Managed repo
	@Autowired
	private SectionRepository	sectionRepository;

	//Supporting services

}
