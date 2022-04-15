/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.table.runtime.arrow.vectors;

import org.apache.flink.annotation.Internal;
import org.apache.flink.table.data.vector.BytesColumnVector;
import org.apache.flink.util.Preconditions;

import org.apache.arrow.vector.VarBinaryVector;

/** Arrow column vector for VarBinary. */
@Internal
public final class ArrowVarBinaryColumnVector implements BytesColumnVector {

    /** Container which is used to store the sequence of varbinary values of a column to read. */
    private final VarBinaryVector varBinaryVector;

    public ArrowVarBinaryColumnVector(VarBinaryVector varBinaryVector) {
        this.varBinaryVector = Preconditions.checkNotNull(varBinaryVector);
    }

    @Override
    public Bytes getBytes(int i) {
        byte[] bytes = varBinaryVector.get(i);
        return new Bytes(bytes, 0, bytes.length);
    }

    @Override
    public boolean isNullAt(int i) {
        return varBinaryVector.isNull(i);
    }
}
