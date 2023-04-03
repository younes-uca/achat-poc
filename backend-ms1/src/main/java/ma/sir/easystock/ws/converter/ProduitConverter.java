package ma.sir.easystock.ws.converter;

import ma.sir.easystock.bean.core.Produit;
import ma.sir.easystock.bean.history.ProduitHistory;
import ma.sir.easystock.ws.dto.ProduitDto;
import ma.sir.easystock.zynerator.converter.AbstractConverter;
import ma.sir.easystock.zynerator.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProduitConverter extends AbstractConverter<Produit, ProduitDto, ProduitHistory> {

    @Autowired
    private CategorieProduitConverter categorieProduitConverter;

    public ProduitConverter() {
        super(Produit.class, ProduitDto.class, ProduitHistory.class);
    }

    @Override
    public Produit toItem(ProduitDto dto) {
        if (dto == null) {
            return null;
        } else {
            Produit item = new Produit();
            if (StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if (StringUtil.isNotEmpty(dto.getReference()))
                item.setReference(dto.getReference());
            if (StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if (StringUtil.isNotEmpty(dto.getBarcode()))
                item.setBarcode(dto.getBarcode());
            if (StringUtil.isNotEmpty(dto.getDiscription()))
                item.setDiscription(dto.getDiscription());
            if (StringUtil.isNotEmpty(dto.getPrixAchatMoyen()))
                item.setPrixAchatMoyen(dto.getPrixAchatMoyen());
            if (StringUtil.isNotEmpty(dto.getQuantite()))
                item.setQuantite(dto.getQuantite());
            if (StringUtil.isNotEmpty(dto.getSeuilAlert()))
                item.setSeuilAlert(dto.getSeuilAlert());
            if (dto.getCategorieProduit() != null) {
                item.setCategorieProduit(categorieProduitConverter.toItem(dto.getCategorieProduit()));
            }

            return item;
        }
    }

    @Override
    public ProduitDto toDto(Produit item) {
        if (item == null) {
            return null;
        } else {
            ProduitDto dto = new ProduitDto();
            if (StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if (StringUtil.isNotEmpty(item.getReference()))
                dto.setReference(item.getReference());
            if (StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if (StringUtil.isNotEmpty(item.getBarcode()))
                dto.setBarcode(item.getBarcode());
            if (StringUtil.isNotEmpty(item.getDiscription()))
                dto.setDiscription(item.getDiscription());
            if (StringUtil.isNotEmpty(item.getPrixAchatMoyen()))
                dto.setPrixAchatMoyen(item.getPrixAchatMoyen());
            if (StringUtil.isNotEmpty(item.getQuantite()))
                dto.setQuantite(item.getQuantite());
            if (StringUtil.isNotEmpty(item.getSeuilAlert()))
                dto.setSeuilAlert(item.getSeuilAlert());

            if (item.getCategorieProduit() != null) {
                dto.setCategorieProduit(categorieProduitConverter.toDto(item.getCategorieProduit()));
            }
            return dto;
        }
    }


    public void initObject(boolean value) {
    }


}
