import { NgModule } from '@angular/core';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';

@NgModule({
  imports: [ToastModule],
  exports: [ToastModule],
  providers: [MessageService],
})
export class PrimeNgModule {}
