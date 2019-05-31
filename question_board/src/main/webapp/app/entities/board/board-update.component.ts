import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBoard } from 'app/shared/model/board.model';
import { BoardService } from './board.service';

@Component({
    selector: 'jhi-board-update',
    templateUrl: './board-update.component.html'
})
export class BoardUpdateComponent implements OnInit {
    board: IBoard;
    isSaving: boolean;
    createdOn: string;

    constructor(private boardService: BoardService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ board }) => {
            this.board = board;
            this.createdOn = this.board.createdOn != null ? this.board.createdOn.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.board.createdOn = this.createdOn != null ? moment(this.createdOn, DATE_TIME_FORMAT) : null;
        if (this.board.id !== undefined) {
            this.subscribeToSaveResponse(this.boardService.update(this.board));
        } else {
            this.subscribeToSaveResponse(this.boardService.create(this.board));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBoard>>) {
        result.subscribe((res: HttpResponse<IBoard>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
