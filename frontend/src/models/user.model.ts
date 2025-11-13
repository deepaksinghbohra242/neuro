export interface LoginCredentials {
  email: string;
  password: string;
}

export interface RegisterData {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  roles: string[];
}

export interface UserProfile {
  id?: number;
  firstName: string;
  lastName: string;
  email: string;
  age?: number;
  roles: string[];
  password?: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface UserModel {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  roles: string[];
  password?: string; 
  age?: number;      
}