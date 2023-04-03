package ma.sir.easystock.dao.facade.core;


import ma.sir.easystock.bean.core.CategorieProduit;
import ma.sir.easystock.bean.core.Produit;
import ma.sir.easystock.zynerator.repository.AbstractRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategorieProduitDao extends AbstractRepository<CategorieProduit,Long>  {
    Produit findByReference(String reference);
    int deleteByReference(String reference);


}
