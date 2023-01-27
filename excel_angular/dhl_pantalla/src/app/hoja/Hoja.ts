export class Hoja {
  awb: string;
  hawb: string;
  consignatario: string;
  pesosB: number;
  piezas: string;
  piezasR: string;
  manifiesto:string;
  periodo:string;

  constructor(awb: string,
    hawb: string,
    consignatario: string,
    pesosb: number,
    piezas: string,
    piezasr: string,
    manifiesto:string,
    periodo:string){
      this.awb = awb;
this.hawb = hawb;
this.consignatario=consignatario;
this.periodo = periodo;
this.pesosB = pesosb;
this.manifiesto = manifiesto;
this.piezas = piezas;
this.piezasR = piezasr;

    }
}
