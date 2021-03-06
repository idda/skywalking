/*
 * Copyright 2017, OpenSkywalking Organization All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Project repository: https://github.com/OpenSkywalking/skywalking
 */

package org.skywalking.apm.collector.core.graph;

/**
 * 提交数据给 Node 的方式
 *
 * @author wusheng
 */
public abstract class WayToNode<INPUT, OUTPUT> {

    /**
     * 目标 Node
     */
    private Node destination;
    /**
     * 目标 Node 的处理器
     */
    private NodeProcessor<INPUT, OUTPUT> destinationHandler;

    public WayToNode(NodeProcessor<INPUT, OUTPUT> destinationHandler) {
        this.destinationHandler = destinationHandler;
    }

    void buildDestination(Graph graph) {
        destination = new Node(graph, destinationHandler);
    }

    protected abstract void in(INPUT input);

    protected void out(INPUT input) {
        destination.execute(input);
    }

    Node getDestination() {
        return destination;
    }
}
