import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { QuestionBoardQuestionModule } from './question/question.module';
import { QuestionBoardBoardModule } from './board/board.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        QuestionBoardQuestionModule,
        QuestionBoardBoardModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class QuestionBoardEntityModule {}
