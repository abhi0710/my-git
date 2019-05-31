import { NgModule } from '@angular/core';

import { QuestionBoardSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [QuestionBoardSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [QuestionBoardSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class QuestionBoardSharedCommonModule {}
