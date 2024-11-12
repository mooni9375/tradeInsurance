package com.trade.insurance.system.app.service.dataaccess.app.adapter;

import com.trade.insurance.system.app.service.dataaccess.app.mapper.AppDataAccessMapper;
import com.trade.insurance.system.app.service.dataaccess.app.repository.AppJpaRepository;
import com.trade.insurance.system.app.service.domain.entity.App;
import com.trade.insurance.system.app.service.domain.ports.output.repository.AppRepository;
import com.trade.insurance.system.app.service.domain.valueobject.TrackingId;

import java.util.Optional;

public class AppRepositoryImpl implements AppRepository {

    private final AppJpaRepository appJpaRepository;
    private final AppDataAccessMapper appDataAccessMapper;

    public AppRepositoryImpl(AppJpaRepository appJpaRepository,
                             AppDataAccessMapper appDataAccessMapper) {
        this.appJpaRepository = appJpaRepository;
        this.appDataAccessMapper = appDataAccessMapper;
    }


    @Override
    public App save(App app) {
        return appDataAccessMapper.appEntityToApp(
                appJpaRepository.save(appDataAccessMapper.appToAppEntity(app))
                );
    }

    @Override
    public Optional<App> findByTrackingId(TrackingId trackingId) {
        return appJpaRepository.findByTrackingId(trackingId.getValue())
                .map(appDataAccessMapper::appEntityToApp);
    }
}





