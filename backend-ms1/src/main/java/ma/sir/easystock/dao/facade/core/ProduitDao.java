package ma.sir.easystock.dao.facade.core;


import ma.sir.easystock.zynerator.repository.AbstractRepository;
import ma.sir.easystock.bean.core.Produit;
import org.springframework.stereotype.Repository;
import ma.sir.easystock.bean.core.Produit;
import java.util.List;


@Repository
public interface ProduitDao extends AbstractRepository<Produit,Long>  {
    Produit findByReference(String reference);
    int deleteByReference(String reference);


}
