import java.lang.*;

public class QueueSimulator {
	public enum Event {
		ARRIVAL, DEPARTURE
	};

	private double currTime;
	private double arrivalRate;
	private double serviceTime;
	private double timeForNextArrival;
	private double timeForNextDeparture;
	private double totalSimTime;
	private LinkedListQueue<Data> buffer;
	private LinkedListQueue<Data> eventQueue;
	private Event e;

	public double getRandTime(final double arrivalRate) {
		final double max = 1.0;
		final double min = 0.0;
		final double randNUM = Math.random();
		final double time1 = -1.0 / arrivalRate * Math.log(1.0 - randNUM);
		return time1;
	}

	public QueueSimulator(final double aR, final double servT, final double simT) {
		buffer = new LinkedListQueue<Data>();
		eventQueue = new LinkedListQueue<Data>();
		currTime = 0.0;

		arrivalRate = aR;
		serviceTime = servT;
		totalSimTime = simT;

		timeForNextArrival = getRandTime(arrivalRate);
		timeForNextDeparture = timeForNextArrival + serviceTime;
	}

	public double calcAverageWaitingTime() {
		final double[] waitingTimes = new double[eventQueue.size()];
		int i = 0;
		while (eventQueue.size() > 0) {
			final Data data = (Data) eventQueue.dequeue();
			waitingTimes[i] = data.getDepartureTime() - data.getArrivalTime();
			++i;
		}
		double sum = 0.0;
		for (int j = 0; j < waitingTimes.length; ++j) {
			sum += waitingTimes[j];
		}
		return sum / waitingTimes.length;
	}

	public double runSimulation() {
		while (currTime < totalSimTime) {
			if (buffer.isEmpty()) {
				timeForNextDeparture = timeForNextArrival + serviceTime;
			}
			e = ((timeForNextArrival < timeForNextDeparture) ? QueueSimulator.Event.ARRIVAL
					: QueueSimulator.Event.DEPARTURE);
			if (e == QueueSimulator.Event.ARRIVAL) {
				currTime = timeForNextArrival;
				final Data data = new Data();
				data.setArrivalTime(currTime);
				buffer.enqueue(data);
				timeForNextArrival = currTime + getRandTime(arrivalRate);
			} else {
				currTime = timeForNextDeparture;
				final Data data = (Data) buffer.dequeue();
				data.setDepartureTime(currTime);
				eventQueue.enqueue(data);
				timeForNextDeparture = currTime + serviceTime;
			}
		}
		return calcAverageWaitingTime();
	}
}