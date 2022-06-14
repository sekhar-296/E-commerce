export class product
{
  photo:any;
  product:any;
  price:any;
  discount:any;
  shopName:any;
  brand:any;
  decription:any;
  inStock:any;
  freeDelivary:any;

  photo1:any;
  brandName:any;
  category:any;
  price1:any;
  off:any;

  constructor(
    photo:any,product:any,price:any,discount:any,shopName:any,brand:any,decription:any,inStock:any,freeDelivary:any,
    photo1:any,brandName:any,category:any,price1:any,off:any
    )
  {
    this.photo=photo;
    this.product=product;
    this.price=price;
    this.discount=discount;
    this.shopName=shopName;
    this.brand=brand;
    this.decription=decription;
    this.inStock=inStock;
    this.freeDelivary=freeDelivary;

    this.photo1=photo1;
    this.brandName=brandName;
    this.category=category;
    this.price1=price1;
    this.off=off;
  }
 }