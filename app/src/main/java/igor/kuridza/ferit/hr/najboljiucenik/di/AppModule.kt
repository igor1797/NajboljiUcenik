package igor.kuridza.ferit.hr.najboljiucenik.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import igor.kuridza.ferit.hr.najboljiucenik.database.*
import igor.kuridza.ferit.hr.najboljiucenik.firebase.geography.FirebaseFlagInteractor
import igor.kuridza.ferit.hr.najboljiucenik.firebase.geography.FirebaseFlagInteractorImpl
import igor.kuridza.ferit.hr.najboljiucenik.firebase.math.FirebaseMathInteractor
import igor.kuridza.ferit.hr.najboljiucenik.firebase.math.FirebaseMathInteractorImpl
import igor.kuridza.ferit.hr.najboljiucenik.firebase.proverb.FirebaseProverbInteractor
import igor.kuridza.ferit.hr.najboljiucenik.firebase.proverb.FirebaseProverbInteractorImpl
import igor.kuridza.ferit.hr.najboljiucenik.firebase.score.ScoreFirebaseInteractor
import igor.kuridza.ferit.hr.najboljiucenik.firebase.score.ScoreFirebaseInteractorImpl
import igor.kuridza.ferit.hr.najboljiucenik.firebase.truefalse.FirebaseTrueFalseInteractor
import igor.kuridza.ferit.hr.najboljiucenik.firebase.truefalse.FirebaseTrueFalseInteractorImpl
import igor.kuridza.ferit.hr.najboljiucenik.persistance.flagsgeography.FlagQuestionRepository
import igor.kuridza.ferit.hr.najboljiucenik.persistance.flagsgeography.FlagQuestionRepositoryImpl
import igor.kuridza.ferit.hr.najboljiucenik.persistance.math.MathQuestionRepository
import igor.kuridza.ferit.hr.najboljiucenik.persistance.math.MathQuestionRepositoryImpl
import igor.kuridza.ferit.hr.najboljiucenik.persistance.proverb.ProverbRepository
import igor.kuridza.ferit.hr.najboljiucenik.persistance.proverb.ProverbRepositoryImpl
import igor.kuridza.ferit.hr.najboljiucenik.persistance.truefalse.TrueFalseQuestionRepository
import igor.kuridza.ferit.hr.najboljiucenik.persistance.truefalse.TrueFalseQuestionRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = DaoProvider.invoke(appContext)

    @Singleton
    @Provides
    fun provideFlagQuestionDao(db: DaoProvider): FlagQuestionDao = db.flagQuestionDao()

    @Singleton
    @Provides
    fun provideTrueFalseQuestionDao(db: DaoProvider): TrueFalseQuestionDao = db.trueFalseQuestionDao()

    @Singleton
    @Provides
    fun provideMathQuestionDao(db: DaoProvider): MathQuestionDao= db.mathQuestionDao()

    @Singleton
    @Provides
    fun provideProverbDao(db: DaoProvider): ProverbDao = db.proverbDao()

    @Singleton
    @Provides
    fun provideFlagQuestionRepository(flagQuestionDao: FlagQuestionDao): FlagQuestionRepository =
        FlagQuestionRepositoryImpl(flagQuestionDao)

    @Singleton
    @Provides
    fun provideTrueFalseQuestionRepository(trueFalseQuestionDao: TrueFalseQuestionDao): TrueFalseQuestionRepository =
        TrueFalseQuestionRepositoryImpl(trueFalseQuestionDao)

    @Singleton
    @Provides
    fun provideMathQuestionRepository(mathQuestionDao: MathQuestionDao): MathQuestionRepository =
        MathQuestionRepositoryImpl(mathQuestionDao)

    @Singleton
    @Provides
    fun provideProverbRepository(proverbDao: ProverbDao): ProverbRepository =
        ProverbRepositoryImpl(proverbDao)

    @Singleton
    @Provides
    fun provideFirebaseMathInteractor(mathQuestionRepository: MathQuestionRepository): FirebaseMathInteractor =
        FirebaseMathInteractorImpl(mathQuestionRepository)

    @Singleton
    @Provides
    fun provideFirebaseFlagInteractor(flagQuestionRepository: FlagQuestionRepository): FirebaseFlagInteractor =
        FirebaseFlagInteractorImpl(flagQuestionRepository)

    @Singleton
    @Provides
    fun provideFirebaseTrueFalseInteractor(trueFalseQuestionRepository: TrueFalseQuestionRepository): FirebaseTrueFalseInteractor =
        FirebaseTrueFalseInteractorImpl(trueFalseQuestionRepository)

    @Singleton
    @Provides
    fun provideFirebaseProverbInteractor(proverbRepository: ProverbRepository): FirebaseProverbInteractor =
        FirebaseProverbInteractorImpl(proverbRepository)

    @Singleton
    @Provides
    fun provideFirebaseScoreInteractor(): ScoreFirebaseInteractor =
        ScoreFirebaseInteractorImpl()
}