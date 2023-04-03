import {Component, OnInit, Input} from '@angular/core';

import {RoleService} from 'src/app/zynerator/security/Role.service';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { environment } from 'src/environments/environment';
import { DatePipe } from '@angular/common';

import { StringUtilService } from 'src/app/zynerator/util/StringUtil.service';
import { AbstractCreateController } from 'src/app/zynerator/controller/AbstractCreateController';

import {AchatService} from 'src/app/controller/service/Achat.service';
import {AchatDto} from 'src/app/controller/model/Achat.model';
import {AchatCriteria} from 'src/app/controller/criteria/AchatCriteria.model';
import {ProduitDto} from 'src/app/controller/model/Produit.model';
import {ProduitService} from 'src/app/controller/service/Produit.service';
import {AchatItemDto} from 'src/app/controller/model/AchatItem.model';
import {AchatItemService} from 'src/app/controller/service/AchatItem.service';
import {ClientDto} from 'src/app/controller/model/Client.model';
import {ClientService} from 'src/app/controller/service/Client.service';
@Component({
  selector: 'app-achat-create-admin',
  templateUrl: './achat-create-admin.component.html'
})
export class AchatCreateAdminComponent extends AbstractCreateController<AchatDto, AchatCriteria, AchatService>  implements OnInit {

    private _achatItemsElement = new AchatItemDto();


   private _validAchatReference = true;
    private _validClientCin = true;
    private _validClientNom = true;
    private _validAchatItemsProduit = true;
    private _validAchatItemsPrixUnitaire = true;
    private _validAchatItemsPrixVente = true;
    private _validAchatItemsQuantite = true;

    constructor(private datePipe: DatePipe, private achatService: AchatService
     , private stringUtilService: StringUtilService, private roleService: RoleService,  private messageService: MessageService
    , private confirmationService: ConfirmationService, private router: Router  
, private produitService: ProduitService, private achatItemService: AchatItemService, private clientService: ClientService
    ) {
        super(datePipe, achatService, messageService, confirmationService, roleService, router, stringUtilService);
    }

    ngOnInit(): void {
        this.achatItemsElement.produit = new ProduitDto();
        this.produitService.findAll().subscribe((data) => this.produits = data);
    this.client = new ClientDto();
    this.clientService.findAll().subscribe((data) => this.clients = data);
}


    validateAchatItems(){
        this.errorMessages = new Array();
        this.validateAchatItemsProduit();
        this.validateAchatItemsPrixUnitaire();
        this.validateAchatItemsPrixVente();
        this.validateAchatItemsQuantite();
    }


    public setValidation(value: boolean){
        this.validAchatReference = value;
        this.validAchatItemsProduit = value;
        this.validAchatItemsPrixUnitaire = value;
        this.validAchatItemsPrixVente = value;
        this.validAchatItemsQuantite = value;
    }

    public addAchatItems() {
        if( this.item.achatItems == null )
            this.item.achatItems = new Array<AchatItemDto>();
       this.validateAchatItems();
       if (this.errorMessages.length === 0) {
              this.item.achatItems.push({... this.achatItemsElement});
              this.achatItemsElement = new AchatItemDto();
       }else{
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs suivant : ' + this.errorMessages});
       }
    }


    public deleteAchatItem(p: AchatItemDto) {
        this.item.achatItems.forEach((element, index) => {
            if (element === p) { this.item.achatItems.splice(index, 1); }
        });
    }

    public editAchatItem(p: AchatItemDto) {
        this.achatItemsElement = {... p};
        this.activeTab = 0;
    }


    public validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateAchatReference();
    }

    public validateAchatReference(){
        if (this.stringUtilService.isEmpty(this.item.reference)) {
        this.errorMessages.push('Reference non valide');
        this.validAchatReference = false;
        } else {
            this.validAchatReference = true;
        }
    }

    public validateAchatItemsProduit(){
        if (this.achatItemsElement.produit == null) {
            this.errorMessages.push('Produit de la achatItem est  invalide');
            this.validAchatItemsProduit = false;
        } else {
            this.validAchatItemsProduit = true;
        }
    }
    public validateAchatItemsPrixUnitaire(){
        if (this.achatItemsElement.prixUnitaire == null) {
            this.errorMessages.push('PrixUnitaire de la achatItem est  invalide');
            this.validAchatItemsPrixUnitaire = false;
        } else {
            this.validAchatItemsPrixUnitaire = true;
        }
    }
    public validateAchatItemsPrixVente(){
        if (this.achatItemsElement.prixVente == null) {
            this.errorMessages.push('PrixVente de la achatItem est  invalide');
            this.validAchatItemsPrixVente = false;
        } else {
            this.validAchatItemsPrixVente = true;
        }
    }
    public validateAchatItemsQuantite(){
        if (this.achatItemsElement.quantite == null) {
            this.errorMessages.push('Quantite de la achatItem est  invalide');
            this.validAchatItemsQuantite = false;
        } else {
            this.validAchatItemsQuantite = true;
        }
    }

    public async openCreateProduit(produit: string) {
    const isPermistted = await this.roleService.isPermitted('Produit', 'add');
    if(isPermistted) {
         this.produit = new ProduitDto();
         this.createProduitDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
    }
    public async openCreateClient(client: string) {
    const isPermistted = await this.roleService.isPermitted('Client', 'add');
    if(isPermistted) {
         this.client = new ClientDto();
         this.createClientDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
    }

    get produit(): ProduitDto {
        return this.produitService.item;
    }
    set produit(value: ProduitDto) {
        this.produitService.item = value;
    }
    get produits(): Array<ProduitDto> {
        return this.produitService.items;
    }
    set produits(value: Array<ProduitDto>) {
        this.produitService.items = value;
    }
    get createProduitDialog(): boolean {
       return this.produitService.createDialog;
    }
    set createProduitDialog(value: boolean) {
        this.produitService.createDialog= value;
    }
    get client(): ClientDto {
        return this.clientService.item;
    }
    set client(value: ClientDto) {
        this.clientService.item = value;
    }
    get clients(): Array<ClientDto> {
        return this.clientService.items;
    }
    set clients(value: Array<ClientDto>) {
        this.clientService.items = value;
    }
    get createClientDialog(): boolean {
       return this.clientService.createDialog;
    }
    set createClientDialog(value: boolean) {
        this.clientService.createDialog= value;
    }



    get validAchatReference(): boolean {
        return this._validAchatReference;
    }

    set validAchatReference(value: boolean) {
         this._validAchatReference = value;
    }

    get validClientCin(): boolean {
        return this._validClientCin;
    }
    set validClientCin(value: boolean) {
        this._validClientCin = value;
    }
    get validClientNom(): boolean {
        return this._validClientNom;
    }
    set validClientNom(value: boolean) {
        this._validClientNom = value;
    }
    get validAchatItemsProduit(): boolean {
        return this._validAchatItemsProduit;
    }
    set validAchatItemsProduit(value: boolean) {
        this._validAchatItemsProduit = value;
    }
    get validAchatItemsPrixUnitaire(): boolean {
        return this._validAchatItemsPrixUnitaire;
    }
    set validAchatItemsPrixUnitaire(value: boolean) {
        this._validAchatItemsPrixUnitaire = value;
    }
    get validAchatItemsPrixVente(): boolean {
        return this._validAchatItemsPrixVente;
    }
    set validAchatItemsPrixVente(value: boolean) {
        this._validAchatItemsPrixVente = value;
    }
    get validAchatItemsQuantite(): boolean {
        return this._validAchatItemsQuantite;
    }
    set validAchatItemsQuantite(value: boolean) {
        this._validAchatItemsQuantite = value;
    }

    get achatItemsElement(): AchatItemDto {
        if( this._achatItemsElement == null )
            this._achatItemsElement = new AchatItemDto();
        return this._achatItemsElement;
    }

    set achatItemsElement(value: AchatItemDto) {
        this._achatItemsElement = value;
    }

}