package fr.mastersid.rio.crypto.backend

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn (ViewModelComponent :: class)
abstract class EstimationBackEndModule {
    @Binds
    abstract fun bindEstimationBackEnd(estimationBackEndImpl: EstimationBackEndImpl):EstimationBackEnd
}