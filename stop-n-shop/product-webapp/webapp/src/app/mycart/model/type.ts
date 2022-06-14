export class Payment {
  constructor(public amt: string, public name: string, public email: string) {}
}

export class UpdatePayment {
  constructor(
    public payment_id: string,
    public order_id: string,
    public status: string
  ) {}
}
