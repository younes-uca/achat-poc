import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';

export class ClientDto  extends BaseDto{

    public id: number;
    public cin: string;
    public nom: string;
    public tel: string;
    public email: string;
    public adresse: FileTempDto[];
    public description: string;
    public creance: number;
    public creanceMax: string ;
    public creanceMin: string ;

}
