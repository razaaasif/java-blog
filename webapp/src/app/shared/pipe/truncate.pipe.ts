import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'truncate'
})
export class TruncatePipe implements PipeTransform {

  transform(value: string, ...args: unknown[]): unknown {
    if (value && args != null && args[0] != null && value.length > Number(args[0])) {
      return Number(args[0]) < 150 ?  value.substring(0, Number(args[0])) + '...' : value;
    }
    return value;
  }

}
