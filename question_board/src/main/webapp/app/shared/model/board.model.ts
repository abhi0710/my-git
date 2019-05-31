import { Moment } from 'moment';
import { IQuestion } from 'app/shared/model//question.model';

export interface IBoard {
    id?: number;
    name?: string;
    active?: boolean;
    createdOn?: Moment;
    questions?: IQuestion[];
}

export class Board implements IBoard {
    constructor(
        public id?: number,
        public name?: string,
        public active?: boolean,
        public createdOn?: Moment,
        public questions?: IQuestion[]
    ) {
        this.active = this.active || false;
    }
}
