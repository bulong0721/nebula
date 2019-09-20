package io.nebula.leaf.controller;

import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.common.Status;
import com.sankuai.inf.leaf.segment.SegmentIDGenImpl;
import com.sankuai.inf.leaf.segment.model.LeafAlloc;
import com.sankuai.inf.leaf.segment.model.SegmentBuffer;
import io.nebula.kernel.exception.NebulaException;
import io.nebula.kernel.exchange.PageableEntity;
import io.nebula.kernel.exchange.ResponseEntity;
import io.nebula.kernel.exchange.StatusCode;
import io.nebula.leaf.service.impl.IDGenServiceImpl;
import io.nebula.leaf.support.SegmentBufferView;
import io.nebula.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class LeafController extends BaseController {
    @Autowired
    private IDGenServiceImpl iDGenService;
    @Autowired
    private SegmentIDGenImpl segmentIDGen;

    @RequestMapping(value = "/segmentDB/get/{key}")
    public ResponseEntity<Long> getSegmentID(@PathVariable("key") String key) {
        long genId = generate(key, (Result) iDGenService.getSegmentID(key));
        return StatusCode.OK.build(genId);
    }

    @RequestMapping(value = "/snowflakeZK/get/{key}")
    public ResponseEntity<Long> getSnowflakeID(@PathVariable("key") String key) {
        long genId = generate(key, (Result) iDGenService.getSnowflakeID(key));
        return StatusCode.OK.build(genId);
    }

    @RequestMapping(value = "/segment/cache")
    public PageableEntity<SegmentBufferView> getCache() {
        List<SegmentBufferView> data = new ArrayList<>();
        Map<String, SegmentBuffer> cache = segmentIDGen.getCache();
        for (Map.Entry<String, SegmentBuffer> entry : cache.entrySet()) {
            SegmentBufferView sv = new SegmentBufferView();
            SegmentBuffer buffer = entry.getValue();
            sv.setInitOk(buffer.isInitOk());
            sv.setKey(buffer.getKey());
            sv.setPos(buffer.getCurrentPos());
            sv.setNextReady(buffer.isNextReady());
            sv.setMax0(buffer.getSegments()[0].getMax());
            sv.setValue0(buffer.getSegments()[0].getValue().get());
            sv.setStep0(buffer.getSegments()[0].getStep());

            sv.setMax1(buffer.getSegments()[1].getMax());
            sv.setValue1(buffer.getSegments()[1].getValue().get());
            sv.setStep1(buffer.getSegments()[1].getStep());
            data.add(sv);
        }
        return StatusCode.OK.build(data, 0, data.size(), data.size());
    }

    @RequestMapping(value = "/segment/gdb")
    public PageableEntity<LeafAlloc> getDb(Model model) {
        List<LeafAlloc> items = segmentIDGen.getAllLeafAllocs();
        return StatusCode.OK.build(items, 0, items.size(), items.size());
    }

    private long generate(@PathVariable("key") String key, Result id) {
        Result result;
        if (key == null || key.isEmpty()) {
            throw new NebulaException("No key");
        }
        result = id;
        if (result.getStatus().equals(Status.EXCEPTION)) {
            throw new NebulaException(result.toString());
        }
        return result.getId();
    }
}
