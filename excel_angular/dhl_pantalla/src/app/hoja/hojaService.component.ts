import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable } from 'rxjs';
import { ExcelDTO } from './ExcelDTO';
import { Hoja } from './Hoja';

@Injectable({
  providedIn: 'root',
})
export class HojaService {
  private baseURL = 'http://localhost:8080/';
  constructor(private httpClient: HttpClient) {}

  getHojaPorAWB(
    periodo: string,
    manifiesto: string,
    awb: string
  ): Observable<Hoja[]> {
    return this.httpClient.get<Hoja[]>(
      this.baseURL.concat('guias/', periodo, '/', manifiesto, '/', awb)
    );
  }

  getHojaPorHawb(
    periodo: string,
    manifiesto: string,
    awb: string,
    hawb: string
  ): Observable<Hoja> {
    return this.httpClient.get<Hoja>(
      this.baseURL.concat(
        'guia/',
        periodo,
        '/',
        manifiesto,
        '/',
        awb,
        '/',
        hawb
      )
    );
  }

  patchHoja(hoja: Hoja): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('periodo',hoja.periodo);
    formData.append('hawb',hoja.hawb);
    formData.append('awb',hoja.awb);
    formData.append('piezasR',hoja.piezasR);
    formData.append('manifiesto',hoja.manifiesto);
    return this.httpClient.patch<any>(
      this.baseURL.concat(
        'guia/',
        hoja.periodo,
        '/',
        hoja.manifiesto,
        '/',
        hoja.awb,
        '/',
        hoja.hawb
      ),
      {
        "piezasR":hoja.piezasR,
        "periodo": hoja.periodo,
        "manifiesto": hoja.manifiesto,
        "awb": hoja.awb,
        "hawb": hoja.hawb,
               
    }
    );
  }

  deleteAwb(periodo: string, manifiesto: string, awb: string): Observable<any> {
    return this.httpClient.delete<any>(
      this.baseURL.concat('eliminarExcel/', periodo, '/', manifiesto, '/', awb)
    );
  }

  postGrabarExcel(e: ExcelDTO): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', e.file);
    formData.append('manifiesto', e.manifiesto);
    formData.append('periodo', e.periodo);
    return this.httpClient.post(this.baseURL.concat('grabar'), formData);
  }
}
