package ${classPackage};

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ${components.dao.classPackage}.${components.dao.className};
import ${components.model.classPackage}.${components.model.className};

/**
 * ${components.model.className} DAO Hibernate implementation.
 * TODO Change ME.
 * 
 * @author ${author}
 *
 */
@Repository
public class ${className} extends GenericDaoHibernate<${components.model.className}, ${table.primaryKey.type}> implements ${components.dao.className} {

	/**
	 * ${className} Constructor.
	 * @param sessionFactory Hibernate SessionFactory
	 */
	@Autowired
	public ${className}(SessionFactory sessionFactory) {
		super(sessionFactory, ${components.model.className}.class);
	}

}
