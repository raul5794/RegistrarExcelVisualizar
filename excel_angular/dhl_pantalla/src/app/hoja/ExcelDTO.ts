export class ExcelDTO{
    file: File;
    manifiesto : string;
    periodo : string;

    constructor(file:File,manifiesto:string,periodo:string){
        this.file = file;
        this.manifiesto = manifiesto;
        this.periodo = periodo;
    }
    
}