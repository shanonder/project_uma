package app.uma.dao.repository;

import org.springframework.data.repository.CrudRepository;

import app.uma.dao.entity.Item;

public interface IItemRepository extends CrudRepository<Item, Long> {

}
