export class StringBuilder {

  strArray: Array<string> = new Array<string>();

  get(nIndex:number): string
  {
    let str:string = null;
    if( (this.strArray.length > nIndex) && (nIndex >= 0) )
    {
      str = this.strArray[nIndex];
    }
    return(str);
  }

  isEmpty(): boolean
  {
    return this.strArray.length === 0;
  }

  append(str: string): void
  {
    if(!str) {
      this.strArray.push(str);
    }
  }

  toString(): string
  {
   return this.joinToString(",");
  }

  joinToString(delimeter: string): string {
    return this.strArray.join(delimeter);
  }

  clear()
  {
    this.strArray.length = 0;
  }

}
