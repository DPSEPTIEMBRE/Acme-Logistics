
package services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Comment;
import domain.Store;
import repositories.CommentRepository;

@Service
@Transactional
public class CommentService {

	//Repository
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	StoreService storeService;

	//Constructor
	public CommentService() {
		super();
	}

	public Comment create() {
		Comment comment = new Comment();

		comment.setCreated(new Date());
		comment.setBody(new String());
		return comment;

	}

	public Comment save(Comment entity, int store_id) {
		Assert.notNull(entity);
		Assert.isTrue(storeService.exists(store_id));
		
		Comment saved = commentRepository.save(entity);
		
		Store store = storeService.findOne(store_id);
		store.getComments().add(saved);
		
		storeService.save(store);
		
		return saved;
	}

	public Comment findOne(Integer id) {
		Assert.notNull(id);
		return commentRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		Assert.notNull(id);
		return commentRepository.exists(id);
	}

	public void delete(Comment entity) {
		Assert.notNull(entity);
		commentRepository.delete(entity);
	}

	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

}
