export interface User {
  id: number;
  idLanguage: number;
  role: 'User' | 'Support' | 'Employee' | 'Admin';
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  birthDate: Date;
  address: string;
}