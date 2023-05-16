import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Order } from '../model/order';

@Component({
  selector: 'app-id-dialog',
  templateUrl: './id-dialog.component.html',
  styleUrls: ['./id-dialog.component.css']
})
export class IdDialogComponent {

  constructor(
    public dialogRef: MatDialogRef<IdDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Order,
  ) { }
  onOkUserDialog(): void {
  }

}
