import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'truncate'
})
export class TruncatePipe implements PipeTransform {

  transform(value: string, ...args: unknown[]): unknown {
    if (value && args != null && args[0] != null) {
      const val = value.substring(0, Number(args[0])) + '...';
      console.log(val + '\n' + val.length);
      return val;
    }
    return value;
  }

}
