package  ma.sir.easystock.ws.dto;

import ma.sir.easystock.zynerator.audit.Log;
import ma.sir.easystock.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import ma.sir.easystock.zynerator.dto.FileTempDto;

import java.math.BigDecimal;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto  extends AuditBaseDto {

    private String cin  ;
    private String nom  ;
    private String tel  ;
    private String email  ;
    private String adresse  ;
    private FileTempDto adresseAsFileTempDto  ;
    private String description  ;
    private BigDecimal creance  ;


    public FileTempDto getAdresseAsFileTempDto() {
        return adresseAsFileTempDto;
    }

    public void setAdresseAsFileTempDto(FileTempDto adresseAsFileTempDto) {
        this.adresseAsFileTempDto = adresseAsFileTempDto;
    }

    public ClientDto(){
        super();
    }



    @Log
    public String getCin(){
        return this.cin;
    }
    public void setCin(String cin){
        this.cin = cin;
    }

    @Log
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }

    @Log
    public String getTel(){
        return this.tel;
    }
    public void setTel(String tel){
        this.tel = tel;
    }

    @Log
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    @Log
    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }

    @Log
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Log
    public BigDecimal getCreance(){
        return this.creance;
    }
    public void setCreance(BigDecimal creance){
        this.creance = creance;
    }




}