package ma.sir.easystock.dao.specification.core;

import ma.sir.easystock.bean.core.CategorieProduit;
import ma.sir.easystock.dao.criteria.core.CategorieProduitCriteria;
import ma.sir.easystock.dao.criteria.core.ProduitCriteria;
import ma.sir.easystock.zynerator.specification.AbstractSpecification;

public class CategorieProduitSpecification extends AbstractSpecification<CategorieProduitCriteria, CategorieProduit> {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("reference", criteria.getReference(), criteria.getReferenceLike());
        addPredicate("libelle", criteria.getLibelle(), criteria.getLibelleLike());
    }

    public CategorieProduitSpecification(CategorieProduitCriteria criteria) {
        super(criteria);
    }

    public CategorieProduitSpecification(CategorieProduitCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
