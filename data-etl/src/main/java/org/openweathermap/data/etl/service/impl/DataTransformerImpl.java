package org.openweathermap.data.etl.service.impl;

import lombok.RequiredArgsConstructor;
import org.openweathermap.data.etl.service.DataTransformer;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DataTransformerImpl implements DataTransformer {

}
