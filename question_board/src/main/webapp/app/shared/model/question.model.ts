import { IBoard } from 'app/shared/model//board.model';

export interface IQuestion {
    id?: number;
    question?: string;
    votes?: number;
    board?: IBoard;
}

export class Question implements IQuestion {
    constructor(public id?: number, public question?: string, public votes?: number, public board?: IBoard) {}
}
