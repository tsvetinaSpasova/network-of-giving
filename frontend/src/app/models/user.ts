import { Gender } from './gender.enum';

export class User {
    id: number;
    username: string;
    password: string;
    name: string; 
    location: string;
    gender: Gender; 
}