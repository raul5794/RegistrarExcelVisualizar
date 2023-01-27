import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { catchError } from 'rxjs';
import { ExcelDTO } from './ExcelDTO';
import { Hoja } from './Hoja';
import { HojaService } from './hojaService.component';
import { NgbdModalContent } from './Modal';

@Component({
  selector: 'app-hoja',
  templateUrl: './hoja.component.html',
  styleUrls: ['./hoja.component.css'],
})
export class HojaComponent implements OnInit {
  hoja!: Hoja;
  hojas!: Hoja[];
  message: string = "primer valor";
  file!: File ;
  loading: boolean = false;
  grabarExcel: boolean = false;
  grabarGuia: boolean = false;
  periodo!: string;
  manifiesto!: string;
  ok: boolean = false;
  errorExcel: boolean = false;
  verGuias: boolean = false;

  @ViewChild("takeInput", {static: false})
  InputVar!: ElementRef;

  constructor(
    private hojaservice: HojaService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.hoja = new Hoja('', '', '', 0, '', '', '', '2023');
  }

  
  
  public onChange(event: any): void {
    this.file = event.target.files[0];

    //event.target.files.length == 1 ? this.fileName = event.target.files[0].name : this.fileName = event.target.files.length + " archivos";
    // this.files = event.target.files;
  }

  public onClickHojas(event: any) {
    this.grabarExcel = false;
    this.grabarGuia = false;
    this.verGuias = true;
    this.hoja = new Hoja('', '', '', 0, '', '', '', '2023');
  }

  public onProcesarExcel(event: any) {
    this.postGrabarExcel(
      new ExcelDTO(this.file, this.manifiesto, this.periodo)
    );
    this.InputVar.nativeElement.value = "";
  }

  public onClickExcel(event: any): void {
    this.grabarExcel = true;
    this.grabarGuia = false;
    this.verGuias = false;
    this.hoja = new Hoja('', '', '', 0, '', '', '', '2023');
  }

  public onClickActualizar(event: any): void {
    console.log(this.message);
    this.patchHoja(this.hoja);
    console.log(this.message);
    
  }

  public onClickHoja(event: any): void {
    this.grabarExcel = false;
    this.verGuias = false;
    this.grabarGuia = true;
    this.hoja = new Hoja('', '', '', 0, '', '', '', '2023');
  }

  public onBuscarGuia(event: any) {
    this.getHoja('2023', this.hoja.manifiesto, this.hoja.awb, this.hoja.hawb);
  }

  public onGuias(event: any) {
    this.getAWB('2023', this.hoja.manifiesto, this.hoja.awb);
  }

  private getHoja(
    periodo: string,
    manifiesto: string,
    awb: string,
    hawb: string
  ) {
    return this.hojaservice
      .getHojaPorHawb(periodo, manifiesto, awb, hawb)
      .subscribe((data) => {
        this.hoja = data;
      });
  }

  private getAWB(periodo: string, manifiesto: string, awb: string) {
    return this.hojaservice
      .getHojaPorAWB(periodo, manifiesto, awb)
      .pipe(
        catchError((e) => {
          throw e;
        })
      )
      .subscribe((data) => {
        this.hojas = data as Hoja[];
      });
  }

  private patchHoja(hoja: Hoja) {
    return this.hojaservice
      .patchHoja(hoja)
      .pipe(
        catchError((e) => {
          this.message = 'No se pudo actualizar';
          const modalRef = this.modalService.open(NgbdModalContent);
    modalRef.componentInstance.name = this.message;
          throw e;
        })
      )
      .subscribe((data) => {
        this.message = 'Se proceso correctamente';
        const modalRef = this.modalService.open(NgbdModalContent);
    modalRef.componentInstance.name = this.message;
      });
  }

  private postGrabarExcel(e: ExcelDTO) {
    return this.hojaservice
      .postGrabarExcel(e)
      .pipe(
        catchError((e) => {
          this.message = 'No se pudo procesar el Excel, Revise los datos ingresados';
          const modalRef = this.modalService.open(NgbdModalContent);
    modalRef.componentInstance.name = this.message;
          throw 'No se pudo procesar el Excel';
        })
      )
      .subscribe((data) => {
        this.message = 'Se proceso correctamente';
        const modalRef = this.modalService.open(NgbdModalContent);
    modalRef.componentInstance.name = this.message;
      });
  }
}
