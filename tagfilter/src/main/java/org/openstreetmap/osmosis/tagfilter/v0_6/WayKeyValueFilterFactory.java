// This software is released into the Public Domain.  See copying.txt for details.
package org.openstreetmap.osmosis.tagfilter.v0_6;

import java.io.File;

import org.openstreetmap.osmosis.core.pipeline.common.TaskConfiguration;
import org.openstreetmap.osmosis.core.pipeline.common.TaskManager;
import org.openstreetmap.osmosis.core.pipeline.common.TaskManagerFactory;
import org.openstreetmap.osmosis.core.pipeline.v0_6.SinkSourceManager;


/**
 * Extends the basic task manager factory functionality with used-node filter task
 * specific common methods.
 * 
 * @author Brett Henderson
 * @author Christoph Sommer
 */
public class WayKeyValueFilterFactory extends TaskManagerFactory {
	private static final String ARG_KEY_VALUE_LIST = "keyValueList";
	private static final String ARG_KEY_VALUE_LIST_File = "keyValueListFile";
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TaskManager createTaskManagerImpl(TaskConfiguration taskConfig) {
		WayKeyValueFilter wayKeyValueFilter;
		
		if (doesArgumentExist(taskConfig, ARG_KEY_VALUE_LIST)) {
			String keyValueList = getStringArgument(taskConfig, ARG_KEY_VALUE_LIST,
					"highway.motorway,highway.motorway_link,highway.trunk,highway.trunk_link");
			wayKeyValueFilter = new WayKeyValueFilter(keyValueList);
		} else {
			String keyValueListFile = getStringArgument(taskConfig, ARG_KEY_VALUE_LIST_File);
			wayKeyValueFilter = new WayKeyValueFilter(new File(keyValueListFile));
		}		
		
		return new SinkSourceManager(
			taskConfig.getId(),
			wayKeyValueFilter,
			taskConfig.getPipeArgs()
		);
	}

}
